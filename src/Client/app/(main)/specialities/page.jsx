'use client';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import React, { useEffect, useRef, useState } from 'react';
import { getAllSpecialities, updateSpeciality } from '../../services/speciality';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { Dropdown } from 'primereact/dropdown';
import { HttpStatusCode } from 'axios';
import { Toast } from 'primereact/toast';

const SpecialitiesPage = () => {
    const [specialities, setSpecialities] = useState([]);
    const [dialogVisible, setDialogVisible] = useState(false);
    const [dialogTitle, setDialogTitle] = useState('');
    const [id, setId] = useState('');
    const [name, setName] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState(true);

    const statues = [
        { name: 'Active', code: true },
        { name: 'Inactive', code: false }
    ];

    const toast = useRef(null);

    const fetchData = async () => {
        const result = await getAllSpecialities();
        console.log(result);
        setSpecialities(result.data.data);
    };

    useEffect(() => {
        fetchData();
    }, []);

    const activeBodyTemplate = (rowData) => {
        return <span
            className={`customer-badge status-${rowData.active ? 'qualified' : 'unqualified'}`}>{rowData.active ? 'Active' : 'Inactive'}</span>;
    };

    const dialogFooter = (
        <div>
            <Button label="Cancel" severity="danger" icon="pi pi-times" onClick={() => setDialogVisible(false)}
                    outlined />
            <Button label="Save" icon="pi pi-check" outlined onClick={async () => await update()} />
        </div>
    );

    const update = async () => {
        const formData = getFormData();
        console.log('form data: ', formData);
        try {
            const result = await updateSpeciality(id, formData);
            if (result.status === HttpStatusCode.Ok) {
                toast.current.show({
                    severity: 'success',
                    summary: 'Success',
                    detail: `Updated successfully`,
                    life: 2000
                });

                await fetchData();
            }
        } catch (error) {
            console.log(error);

            toast.current.show({
                severity: 'danger',
                summary: 'Failed',
                detail: `Updated failed`,
                life: 2000
            });
        } finally {
            setDialogVisible(false);
        }
    };

    const getFormData = () => {
        return {
            name: name,
            description: description,
            active: status
        };
    };

    const openDialog = (title, rowData) => {
        setDialogTitle(title);
        setDialogVisible(true);
        setId(rowData.id);
        setName(rowData.name);
        setDescription(rowData.description);
        setStatus(rowData.active);
    };

    return (
        <>
            <Toast ref={toast} />
            <Dialog header={dialogTitle}
                    footer={dialogFooter}
                    visible={dialogVisible}
                    style={{ width: '25vw' }}
                    onHide={() => setDialogVisible(false)}>
                <div className="field">
                    <label htmlFor="name" className="block font-medium mb-2">
                        Name
                    </label>
                    <InputText id="name"
                               type="text"
                               value={name}
                               onChange={(e) => setName(e.target.value)}
                               className="w-full md:w-30rem mb-3" />
                </div>
                <div className="field">
                    <label htmlFor="description" className="block font-medium mb-2">
                        Description
                    </label>
                    <InputTextarea id="description"
                                   type="text"
                                   value={description}
                                   onChange={(e) => setDescription(e.target.value)}
                                   rows="3"
                                   className="w-full md:w-30rem mb-3" />
                </div>
                <div className="field">
                    <label htmlFor="status" className="block font-medium mb-2">
                        Status
                    </label>
                    <Dropdown id="status"
                              value={status}
                              onChange={(e) => setStatus(e.target.value)}
                              options={statues}
                              optionLabel="name"
                              optionValue="code"
                              placeholder="Select"
                              className="w-full md:w-30rem mb-3" />
                </div>
            </Dialog>
            <div className="grid">
                <div className="col-12">
                    <div className="card">
                        <h5>Pending Doctors</h5>
                        <DataTable value={specialities}>
                            <Column field="index" header="#"
                                    body={(rowData, rowIndex) => rowIndex.rowIndex + 1}></Column>
                            <Column field="name" header="Name"></Column>
                            <Column field="description" header="Description"></Column>
                            <Column field="active" header="Status" dataType="boolean"
                                    style={{ minWidth: '8rem' }} body={activeBodyTemplate} />
                            <Column header="Action" body={(rowData) => (
                                <div>
                                    <Button icon="pi pi-pencil" severity="primary" rounded text
                                            onClick={() => openDialog('Update', rowData)} />
                                    <Button icon="pi pi-trash" severity="danger" rounded text />
                                </div>
                            )}></Column>
                        </DataTable>
                    </div>
                </div>
            </div>
        </>
    );
};

export default SpecialitiesPage;
